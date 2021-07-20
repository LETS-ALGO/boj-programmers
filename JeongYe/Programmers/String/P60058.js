function solution(p) {
  if (!p) return "";
  if (isCorrectString(p)) return p;

  let splitIndex = 0;
  let map = {};

  //균형잡힌 문자열로 쪼개기 위해 인덱스를 찾는다.
  for (let i = 0; i < p.length; i++) {
    map[p[i]] = (map[p[i]] || 0) + 1;
    const pairBracket = p[i] === "(" ? ")" : "(";
    if (map[p[i]] === map[pairBracket]) {
      splitIndex = i;
      break;
    }
  }

  const u = p.slice(0, splitIndex + 1);
  const v = p.slice(splitIndex + 1);

  if (u[0] === "(") return u + solution(v);

  return "(" + solution(v) + ")" + reverseBrackets(u);
}

//문자열의 첫번째,마지막 문자를 제거하고 나머지 괄호 방향을 뒤집는다. 
function reverseBrackets(str) {
  return Array.from(str.slice(1, -1))
    .map((s) => (s === ")" ? "(" : ")"))
    .join("");
}

//올바른 괄호 문자열인지 검사한다.
function isCorrectString(str) {
  let stack = [];

  Array.from(str).forEach((s) => {
    if (s === "(") {
      stack.push(s);
    } else {
      if (stack[stack.length - 1] === "(") stack.pop();
      else stack.push(s);
    }
  });

  return stack.length === 0 ? true : false;
}

// const p = ")(";
const p = "()))((()";
console.log(solution(p));
