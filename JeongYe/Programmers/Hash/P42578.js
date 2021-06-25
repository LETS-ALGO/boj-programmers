function solution(clothes) {
  let answer = 1;
  let map = {};

  //옷 종류별 개수를 구한다.
  clothes.forEach(([clothes, type]) => {
    map[type] = (map[type] || 0) + 1;
  });

  //특정 종류의 옷을 안입는 경우를 포함하여 +1한 값을 곱해서 조합의 개수를 구한다.
  Object.values(map).forEach((count) => {
    answer *= count + 1;
  });

  //최소 한개의 의상은 입어야 하므로, 아무것도 안입은 경우를 -1 해준다.
  return answer - 1;
}
