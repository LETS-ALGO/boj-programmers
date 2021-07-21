function solution(s) {
  let tempStr = "";
  let cutStr = "";
  let count = 1;
  let compressedArr = [s]; //압축된 문자열을 담는 배열

  //문자열은 최대 s.length/2 만큼 자를 수 있음
  //문자 청크 길이를 1~s.length/2 까지 순서대로 자른다
  for (let chunkSize = 1; chunkSize <= s.length / 2; chunkSize++) {
    cutStr = s.slice(0, chunkSize); //0번 인덱스부터 chunkSize길이 만큼 문자를 자른다.
    tempStr = "";

    for (let j = chunkSize; j <= s.length; j += chunkSize) {
      const current = s.slice(j, j + chunkSize);
      if (cutStr === current) {
        count++;
      } else {
        tempStr += count > 1 ? `${count}${cutStr}` : cutStr;
        cutStr = current;
        count = 1;
      }
    }

    tempStr += count > 1 ? `${count}${cutStr}` : cutStr;
    
    //기존 문자열보다 압축율이 높은 경우만 배열에 넣는다. 
    if (tempStr.length < s.length) compressedArr.push(tempStr);
  }
  
  //압축된 문자열 중 가장 길이가 작은 것을 반환한다.
  return Math.min(...compressedArr.map((s) => s.length));
}

