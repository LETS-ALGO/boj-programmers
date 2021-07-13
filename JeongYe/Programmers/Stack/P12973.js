function solution(s) {
  let stack = [];

  for (let i = 0; i < s.length; i++) {
    //stack의 pop과 동일 하다면 스택에서 제거
    if (stack[stack.length - 1] === s[i]) {
      stack.pop();
    } else {
      stack.push(s[i]);
    }
  }

  return stack.length ? 0 : 1;
}
