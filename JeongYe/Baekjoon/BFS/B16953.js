const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const [start, target] = input[0].split(" ").map((s) => parseInt(s));
  solution(start, target);
});

function solution(start, target) {
  const operators = ["*", "+"];

  //! 놓친 부분
  // 그냥 BFS 돌리면서 카운팅하면 타깃까지 전체 연산 횟수가 카운팅된다.
  // 각 숫자별로 누적 연산 수를 카운팅하려면 다음 숫자 탐색할때 이전 연산 횟수 +1 을 해주면된다.

  //숫자별 누적 연산 횟수를 저장한다.
  let visitCount = { [start]: 1 };

  function search() {
    let queue = [];

    queue.push(start);

    while (queue.length) {
      const current = queue.shift();

      //타깃 값에 도달하면 루프를 빠져나온다.
      if (current === target) break;

      for (let op of operators) {
        const nextNumber = operation(op, current);

        if (nextNumber > target) break;
        queue.push(nextNumber);

        //이전 숫자 연산 횟수에 +1을 한다.
        visitCount[nextNumber] = visitCount[current] + 1;
      }
    }
  }

  search();
  console.log(visitCount[target] || -1);
}

function operation(type, number) {
  return type === "*" ? number * 2 : parseInt(number + "1");
}
