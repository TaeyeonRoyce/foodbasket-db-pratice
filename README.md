# 냉장고 관리 서비스

# 기능

식료품 보관 상태점검 서비스 제공

# 요구사항

### **냉장고, 냉동실 구분 가능**

> 식료품 필드에 보관방식 필드 추가.

**식료품 생성 ✅**

> 생성시 입력(선택) 하는 필드
> `FoodSaveRequestDto`를 통해 데이터 받기.
>
> | FoodSaveRequestDto | 이름   | 보관방법     | 분류         | 수량 | 보관일자   | 유통기한   |
> | ------------------ | ------ | ------------ | ------------ | ---- | ---------- | ---------- |
> | Type               | String | Enum(String) | Enum(String) | int  | LocalDate  | LocalDate  |
> | Nullable           | X      | X            | X            | X    | X          | O          |
> | default            | X      | X            | X            | 1    | today      | X          |
> | ex)                | 우유   | 냉장보관     | 유제품       | 3    | 2022-02-11 | 2022-02-21 |
>
> GET : `/newfood` => 식료품 추가 화면으로 이동
>
> POST : `/newfood` => 식료품 추가
> body => {"food" : "", "storeway" : "", "category" : "", "quantity" : "", "saveAt" : "", "expireAt" : ""}
>
> 저장시 보관일자와 유통기한을 통해 `Food`의 `FoodStatus`를 결정한 뒤 `Food`로 변경하여 DB에 저장



**식료품 조회 ✅**

> 조회 종류
>
> - 전체 조회
>
>   GET :  `/` => 메인 페이지. 모든 식료품 조회
>
> - 카테고리 선택 조회 
>   GET : `/?category={category}` => category에 해당하는 식료품만 조회
>
> - 보관 방법 선택 조회
>
>   GET : `/?storeway={storeway}` => storeway(chilled, frozen) 식료품 조회
>
> - 음식 상태 선택 조회 
>
>   GET : `/?foodstatus={foodstatus}` => foodStatus인 식료품만 조회
>
> *이슈*
>
> - DB에서 직접적으로 선택적 조회를 하면 조회 횟수가 많아져 성능 저하를 일으킬 수 있음.

U : 저장한 식료품의 내용을 수정할 수 있음

> GET : `/edit/{foodId}` => 식료품 수정 화면으로 이동
>
> POST : `/edit/{foodId}` => 식료품 수정 by Id 
> body => {"food" : "", "storeway" : "", "category" : "", "quantity" : "", "saveAt" : "", "expireAt" : ""}
>
> `FoodUpdateRequestDto`를 통해 데이터 받기. 필드와 메서드가 동일하여 `FoodSaveRequestDto`를 상속받음. ✅
>
> 금일 날짜와 비교하여 제품 상태 업데이트 기능 구현 
>
> 1. 조회시 업데이트 로직 수행
> 2. 업데이트 날짜를 저장하여 날짜 변경시에만 업데이트 수행하도록 구현

D : 저장한 식료품을 삭제할 수 있음

### **식료품**

- food

  > String, Range(1 ~ 20), Not Null

- category (ex. 육류, 채소, 과일, 유제품, 기타)

  > 제공된 Category선택, Not Null

- quantity

  > int, Min = 1, Not Null

- saveAt

  > LocalDateTime, Not Null, default : 오늘 날짜

- expireAt

  > LocalDateTime, Nullable

- FoodStatus(?) responseDto에만 존재하도록 할 예정

  > 현재 날짜, 유통기한 datediff로 판단 후 알맞은 Enum 저장
  >
  > 유통기한이 없다면 FoodStatus.NONE으로 저장
  >
  > FoodStatus
  >
  > .NONE
  >
  > .FRESH
  >
  > .CAUTION
  >
  > .EXPIRED

### **페이징(?)**

> baseURI : ~/foodbasket

- GET : `/` => 메인 페이지. 모든 식료품 조회

- GET : `/?category={category}` => category에 해당하는 식료품만 조회

- GET : `/?foodstatus={foodstatus}` => foodStatus인 식료품만 조회

- GET : `/?storeway={storeway}` => storeway(chilled, frozen) 식료품 조회

- GET : `/newfood` => 식료품 추가 화면으로 이동

- POST : `/newfood` => 식료품 추가

  body => {"food" : "", "category" : "", "quantity" : "", "saveAt" : "", "expireAt" : ""}

- GET : `/{foodId}` => foodId에 해당하는 식료품 상세화면 이동

- PUT : `/{foodId}` => 식료품 업데이트 body => {"food" : "", "category" : "", "quantity" : "", "saveAt" : "", "expireAt" : ""} redirect

- DELETE : `/{foodId}` => 식료품 삭제

### **추가 요소**

- 검증
- 성능 개선 -> DB row가 작으면 한번에 전체 조회하여 보여주는게 유리 할 것 같고, DB row가 많으면 필요한 부분에 대해서만 조회하는 것이 유리 할 것 같다.