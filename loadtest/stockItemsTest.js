import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 100 },   // 1분 동안 100 VUs까지 증가
        { duration: '1m', target: 300 },   // 그 다음 1분 동안 300 VUs
        { duration: '1m', target: 600 },   // 1분 동안 600 VUs
        { duration: '1m', target: 1000 },  // 1분 동안 1000 VUs
        { duration: '2m', target: 1000 },  // 2분간 1000 VUs 유지
        { duration: '1m', target: 0 },     // 마지막 1분간 점진적 종료
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95% 요청이 500ms 이하
        http_req_failed: ['rate<0.01'],   // 실패율 1% 미만
    },
};

export default function () {
    http.get('http://localhost:8600/api/v1/stockItems');
    sleep(1); // 초당 1회 요청
}
