class Pair {
  //(숫자, 연산자목록)
  constructor(num, operators) {
    this.num = num;
    this.operators = operators;
  }
}

// 최소 연산 횟수를 구하는 것이므로 DFS가 아닌 BFS를 사용한다.
function solution(s, t) {
  let ops = ["*", "+", "-", "/"];
  let visited = [];
  let result = "";

  function bfs() {
    let queue = [];
    queue.push(new Pair(s, ""));

    while (queue.length) {
      let { num, operators } = queue.shift();

      if (num === t) {
        result = operators;
        break;
      }

      for (let op of ops) {
        let nextVal = 0;
        if (op === "*") nextVal = num * num;
        if (op === "+") nextVal = num + num;
        if (op === "-") nextVal = num - num;
        if (op === "/") {
          if (num === 0) continue;
          nextVal = num / num;
        }

        //방문하지 않은 노드라면 방문 배열에 추가하고, 큐에 Pair(값, 연산자) 쌍을 추가한다.
        if (!visited.includes(nextVal)) {
          visited.push(nextVal);
          queue.push(new Pair(nextVal, operators + op));
        }
      }
    }
  }

  if (s === t) return 0;
  bfs();

  return result || -1;
}

