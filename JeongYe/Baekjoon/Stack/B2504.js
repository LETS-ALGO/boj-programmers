const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const str = input[0];
  solution(str);
});

function solution(str) {
  let stack = [];

  const pairBracket = {
    ")": "(",
    "]": "[",
  };

  for (let s of Array.from(str)) {
    //여는 괄호는 바로 스택에 넣는다.
    if (s === "(" || s === "[") {
      stack.push(s);
    } else {
      const top = stack[stack.length - 1];

      //닫는 괄호와 top이 짝이 맞는다면 top을 꺼내고 스택에 값을 넣는다.
      // () : 2 , [] : 3
      if (s === ")") {
        if (top === pairBracket[s]) {
          stack.pop();
          stack.push(2);
        } else {
          //괄호 사이의 값들 확인
          stack = checkInnerBrackets(stack, s);
        }
      } else if (s === "]") {
        if (top === pairBracket[s]) {
          stack.pop();
          stack.push(3);
        } else {
          //괄호 사이의 값들 확인
          stack = checkInnerBrackets(stack, s);
        }
      }
    }
  }

  //스택 안의 숫자를 모두 더한다.
  let sum = 0;
  while (stack.length) {
    const top = stack.pop();
    //최종적으로 스택에 괄호가 껴있다면 올바르지 않은 경우로 0을 리턴한다.
    if (["(", ")", "[", "]"].includes(top)) {
      console.log(0);
      return;
    }
    sum += top;
  }

  console.log(sum);
}

function checkInnerBrackets(stack, current) {
  let _stack = [...stack];
  let sum = 0;

  //stack 하위까지 검사하면서 current 괄호와 짝이 맞는 괄호가 있는지 검사한다.
  while (_stack.length) {
    const top = _stack[_stack.length - 1];
    if (typeof top === "number") {
      sum += top;
      _stack.pop();
    } else {
      //짝이 맞는 괄호가 있다면 괄호 사이의 숫자들을 모두 더한다.
      //괄호가 () 라면 sum에 2를 곱한다.
      //괄호가 [] 라면 sum에 3을 곱한다.
      if (current === ")" && top === "(") {
        sum = sum * 2;
      } else if (current === "]" && top === "[") {
        sum = sum * 3;
      } else {
        //스택 하위에 current 괄호의 짝이 없다면 스택에 넣고 리턴한다.
        _stack.push(current);
        return _stack;
      }

      //짝이되는 괄호를 제거하고 계산된 값을 넣는다.
      _stack.pop();
      _stack.push(sum);

      return _stack;
    }
  }
  return _stack;
}
