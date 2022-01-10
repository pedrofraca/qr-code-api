import http from 'k6/http';

import { sleep } from 'k6';

export const options = {

    stages: [
      { duration: '30s', target: 100 },
  
      { duration: '1m30s', target: 300 },
  
      { duration: '20s', target: 400 },
  
    ],
  
  };

export default function () {
    const body = {data: "bitcoin:anotehuntaoheuna"};
    const result = http.post('http://localhost:8080/generate', JSON.stringify(body) , {
        headers: { 'Content-Type': 'application/json' },
      } );
    sleep(1);
}