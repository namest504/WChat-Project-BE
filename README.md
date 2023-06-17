# WChat-Project

<br/>

## 프로젝트 소개

소켓기반 실시간 채팅 웹 서비스입니다.

<br/>

## BE Using Stack

- Language : JAVA
- FrameWork : Spring Boot
- DataBase : MariaDB
- DevOps : AWS
- Library : Spring Security, JPA, QueryDSL, STOMP

<br/>

## Implemented API List

<article id="95c1e33c-7ff9-4d5b-afdd-19eefea96848" class="page sans"><header><h1 class="page-title"></h1><p class="page-description"></p></header><div class="page-body"><div id="523e8533-59b7-4884-bedd-f46454f132cc" class="collection-content"><h4 class="collection-title">회원 API</h4><table class="collection-content"><thead><tr><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesTitle"><path d="M0.637695 13.1914C1.0957 13.1914 1.32812 13 1.47852 12.5215L2.24414 10.3887H6.14746L6.90625 12.5215C7.05664 13 7.2959 13.1914 7.74707 13.1914C8.22559 13.1914 8.5332 12.9043 8.5332 12.4531C8.5332 12.2891 8.50586 12.1523 8.44434 11.9678L5.41602 3.79199C5.2041 3.21777 4.82129 2.9375 4.19922 2.9375C3.60449 2.9375 3.21484 3.21777 3.0166 3.78516L-0.0322266 12.002C-0.09375 12.1797 -0.121094 12.3232 -0.121094 12.4668C-0.121094 12.918 0.166016 13.1914 0.637695 13.1914ZM2.63379 9.12402L4.17871 4.68066H4.21973L5.76465 9.12402H2.63379ZM12.2793 13.2324C13.3115 13.2324 14.2891 12.6787 14.7129 11.8037H14.7402V12.5762C14.7471 12.9863 15.0273 13.2393 15.4238 13.2393C15.834 13.2393 16.1143 12.9795 16.1143 12.5215V8.00977C16.1143 6.49902 14.9658 5.52148 13.1543 5.52148C11.7666 5.52148 10.6592 6.08887 10.2695 6.99121C10.1943 7.15527 10.1533 7.3125 10.1533 7.46289C10.1533 7.81152 10.4062 8.04395 10.7686 8.04395C11.0215 8.04395 11.2129 7.94824 11.3496 7.73633C11.7529 6.99121 12.2861 6.65625 13.1064 6.65625C14.0977 6.65625 14.6992 7.20996 14.6992 8.1123V8.67285L12.5664 8.7959C10.7686 8.8916 9.77734 9.69824 9.77734 11.0107C9.77734 12.3369 10.8096 13.2324 12.2793 13.2324ZM12.6621 12.1387C11.8008 12.1387 11.2129 11.667 11.2129 10.9561C11.2129 10.2725 11.7598 9.82129 12.7578 9.75977L14.6992 9.62988V10.3203C14.6992 11.3457 13.7969 12.1387 12.6621 12.1387Z"></path></svg></span>기능명</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesText"><path d="M1.56738 3.25879H14.4258C14.7676 3.25879 15.0479 2.97852 15.0479 2.63672C15.0479 2.29492 14.7744 2.02148 14.4258 2.02148H1.56738C1.21875 2.02148 0.952148 2.29492 0.952148 2.63672C0.952148 2.97852 1.22559 3.25879 1.56738 3.25879ZM1.56738 6.84082H14.4258C14.7676 6.84082 15.0479 6.56055 15.0479 6.21875C15.0479 5.87695 14.7744 5.60352 14.4258 5.60352H1.56738C1.21875 5.60352 0.952148 5.87695 0.952148 6.21875C0.952148 6.56055 1.22559 6.84082 1.56738 6.84082ZM1.56738 10.4229H14.4258C14.7676 10.4229 15.0479 10.1426 15.0479 9.80078C15.0479 9.45898 14.7744 9.18555 14.4258 9.18555H1.56738C1.21875 9.18555 0.952148 9.45898 0.952148 9.80078C0.952148 10.1426 1.22559 10.4229 1.56738 10.4229ZM1.56738 14.0049H8.75879C9.10059 14.0049 9.38086 13.7246 9.38086 13.3828C9.38086 13.041 9.10742 12.7676 8.75879 12.7676H1.56738C1.21875 12.7676 0.952148 13.041 0.952148 13.3828C0.952148 13.7246 1.22559 14.0049 1.56738 14.0049Z"></path></svg></span>함수명</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesMultipleSelect"><path d="M1.91602 4.83789C2.44238 4.83789 2.87305 4.40723 2.87305 3.87402C2.87305 3.34766 2.44238 2.91699 1.91602 2.91699C1.38281 2.91699 0.952148 3.34766 0.952148 3.87402C0.952148 4.40723 1.38281 4.83789 1.91602 4.83789ZM5.1084 4.52344H14.3984C14.7607 4.52344 15.0479 4.23633 15.0479 3.87402C15.0479 3.51172 14.7607 3.22461 14.3984 3.22461H5.1084C4.74609 3.22461 4.45898 3.51172 4.45898 3.87402C4.45898 4.23633 4.74609 4.52344 5.1084 4.52344ZM1.91602 9.03516C2.44238 9.03516 2.87305 8.60449 2.87305 8.07129C2.87305 7.54492 2.44238 7.11426 1.91602 7.11426C1.38281 7.11426 0.952148 7.54492 0.952148 8.07129C0.952148 8.60449 1.38281 9.03516 1.91602 9.03516ZM5.1084 8.7207H14.3984C14.7607 8.7207 15.0479 8.43359 15.0479 8.07129C15.0479 7.70898 14.7607 7.42188 14.3984 7.42188H5.1084C4.74609 7.42188 4.45898 7.70898 4.45898 8.07129C4.45898 8.43359 4.74609 8.7207 5.1084 8.7207ZM1.91602 13.2324C2.44238 13.2324 2.87305 12.8018 2.87305 12.2686C2.87305 11.7422 2.44238 11.3115 1.91602 11.3115C1.38281 11.3115 0.952148 11.7422 0.952148 12.2686C0.952148 12.8018 1.38281 13.2324 1.91602 13.2324ZM5.1084 12.918H14.3984C14.7607 12.918 15.0479 12.6309 15.0479 12.2686C15.0479 11.9062 14.7607 11.6191 14.3984 11.6191H5.1084C4.74609 11.6191 4.45898 11.9062 4.45898 12.2686C4.45898 12.6309 4.74609 12.918 5.1084 12.918Z"></path></svg></span>method</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesText"><path d="M1.56738 3.25879H14.4258C14.7676 3.25879 15.0479 2.97852 15.0479 2.63672C15.0479 2.29492 14.7744 2.02148 14.4258 2.02148H1.56738C1.21875 2.02148 0.952148 2.29492 0.952148 2.63672C0.952148 2.97852 1.22559 3.25879 1.56738 3.25879ZM1.56738 6.84082H14.4258C14.7676 6.84082 15.0479 6.56055 15.0479 6.21875C15.0479 5.87695 14.7744 5.60352 14.4258 5.60352H1.56738C1.21875 5.60352 0.952148 5.87695 0.952148 6.21875C0.952148 6.56055 1.22559 6.84082 1.56738 6.84082ZM1.56738 10.4229H14.4258C14.7676 10.4229 15.0479 10.1426 15.0479 9.80078C15.0479 9.45898 14.7744 9.18555 14.4258 9.18555H1.56738C1.21875 9.18555 0.952148 9.45898 0.952148 9.80078C0.952148 10.1426 1.22559 10.4229 1.56738 10.4229ZM1.56738 14.0049H8.75879C9.10059 14.0049 9.38086 13.7246 9.38086 13.3828C9.38086 13.041 9.10742 12.7676 8.75879 12.7676H1.56738C1.21875 12.7676 0.952148 13.041 0.952148 13.3828C0.952148 13.7246 1.22559 14.0049 1.56738 14.0049Z"></path></svg></span>비고</th></tr></thead><tbody><tr id="a59683c0-ee3b-47eb-b1f3-5d5247bb7e4e"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%8F%E1%85%A1%E1%84%8F%E1%85%A1%E1%84%8B%E1%85%A9%20%E1%84%85%E1%85%A9%E1%84%80%E1%85%B3%E1%84%8B%E1%85%B5%E1%86%AB%20a59683c0ee3b47ebb1f35d5247bb7e4e.html">카카오 로그인</a></td><td class="cell-:RmV">kakaoLogin</td><td class="cell-MLtF"><span class="selected-value select-value-color-blue">GET</span></td><td class="cell-nxQS"></td></tr><tr id="839af4f6-1b79-4c49-9267-c870a865b6a5"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%85%E1%85%A9%E1%84%80%E1%85%B3%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%BA%20839af4f61b794c499267c870a865b6a5.html">로그아웃</a></td><td class="cell-:RmV">logout</td><td class="cell-MLtF"><span class="selected-value select-value-color-green">POST</span></td><td class="cell-nxQS"></td></tr><tr id="b8a6e9d9-d165-42fe-8bf7-e74daf4b540c"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%90%E1%85%A1%E1%86%AF%E1%84%90%E1%85%AC%20b8a6e9d9d16542fe8bf7e74daf4b540c.html">회원 탈퇴</a></td><td class="cell-:RmV">withdrawal</td><td class="cell-MLtF"><span class="selected-value select-value-color-pink">DELETE</span></td><td class="cell-nxQS"></td></tr><tr id="3d5a2fb3-af59-45d2-bbcc-442497fa044f"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%82%E1%85%A2%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%87%E1%85%A9%20%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC%203d5a2fb3af5945d2bbcc442497fa044f.html">내 정보 조회</a></td><td class="cell-:RmV">myNickName</td><td class="cell-MLtF"><span class="selected-value select-value-color-blue">GET</span></td><td class="cell-nxQS"></td></tr><tr id="5460f0f2-9cd3-4c09-a5bf-b531ff169b2d"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%82%E1%85%B5%E1%86%A8%E1%84%82%E1%85%A6%E1%84%8B%E1%85%B5%E1%86%B7%20%E1%84%87%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%205460f0f29cd34c09a5bfb531ff169b2d.html">닉네임 변경</a></td><td class="cell-:RmV">changeMyNickName</td><td class="cell-MLtF"><span class="selected-value select-value-color-orange">PUT</span></td><td class="cell-nxQS"></td></tr><tr id="99abfbc5-bc0f-4b5e-889e-de8b4385edb0"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%92%E1%85%AC%E1%84%8B%E1%85%AF%E1%86%AB%20API%20523e853359b74884beddf46454f132cc/%E1%84%90%E1%85%A9%E1%84%8F%E1%85%B3%E1%86%AB%20%E1%84%8C%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B3%E1%86%B8%2099abfbc5bc0f4b5e889ede8b4385edb0.html">토큰 재발급</a></td><td class="cell-:RmV">useRefreshToken</td><td class="cell-MLtF"><span class="selected-value select-value-color-green">POST</span></td><td class="cell-nxQS"></td></tr></tbody></table></div><div id="d552484a-dae2-4447-a8a8-0c44b76373bd" class="collection-content"><h4 class="collection-title">채팅방 API</h4><table class="collection-content"><thead><tr><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesTitle"><path d="M0.637695 13.1914C1.0957 13.1914 1.32812 13 1.47852 12.5215L2.24414 10.3887H6.14746L6.90625 12.5215C7.05664 13 7.2959 13.1914 7.74707 13.1914C8.22559 13.1914 8.5332 12.9043 8.5332 12.4531C8.5332 12.2891 8.50586 12.1523 8.44434 11.9678L5.41602 3.79199C5.2041 3.21777 4.82129 2.9375 4.19922 2.9375C3.60449 2.9375 3.21484 3.21777 3.0166 3.78516L-0.0322266 12.002C-0.09375 12.1797 -0.121094 12.3232 -0.121094 12.4668C-0.121094 12.918 0.166016 13.1914 0.637695 13.1914ZM2.63379 9.12402L4.17871 4.68066H4.21973L5.76465 9.12402H2.63379ZM12.2793 13.2324C13.3115 13.2324 14.2891 12.6787 14.7129 11.8037H14.7402V12.5762C14.7471 12.9863 15.0273 13.2393 15.4238 13.2393C15.834 13.2393 16.1143 12.9795 16.1143 12.5215V8.00977C16.1143 6.49902 14.9658 5.52148 13.1543 5.52148C11.7666 5.52148 10.6592 6.08887 10.2695 6.99121C10.1943 7.15527 10.1533 7.3125 10.1533 7.46289C10.1533 7.81152 10.4062 8.04395 10.7686 8.04395C11.0215 8.04395 11.2129 7.94824 11.3496 7.73633C11.7529 6.99121 12.2861 6.65625 13.1064 6.65625C14.0977 6.65625 14.6992 7.20996 14.6992 8.1123V8.67285L12.5664 8.7959C10.7686 8.8916 9.77734 9.69824 9.77734 11.0107C9.77734 12.3369 10.8096 13.2324 12.2793 13.2324ZM12.6621 12.1387C11.8008 12.1387 11.2129 11.667 11.2129 10.9561C11.2129 10.2725 11.7598 9.82129 12.7578 9.75977L14.6992 9.62988V10.3203C14.6992 11.3457 13.7969 12.1387 12.6621 12.1387Z"></path></svg></span>기능명</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesText"><path d="M1.56738 3.25879H14.4258C14.7676 3.25879 15.0479 2.97852 15.0479 2.63672C15.0479 2.29492 14.7744 2.02148 14.4258 2.02148H1.56738C1.21875 2.02148 0.952148 2.29492 0.952148 2.63672C0.952148 2.97852 1.22559 3.25879 1.56738 3.25879ZM1.56738 6.84082H14.4258C14.7676 6.84082 15.0479 6.56055 15.0479 6.21875C15.0479 5.87695 14.7744 5.60352 14.4258 5.60352H1.56738C1.21875 5.60352 0.952148 5.87695 0.952148 6.21875C0.952148 6.56055 1.22559 6.84082 1.56738 6.84082ZM1.56738 10.4229H14.4258C14.7676 10.4229 15.0479 10.1426 15.0479 9.80078C15.0479 9.45898 14.7744 9.18555 14.4258 9.18555H1.56738C1.21875 9.18555 0.952148 9.45898 0.952148 9.80078C0.952148 10.1426 1.22559 10.4229 1.56738 10.4229ZM1.56738 14.0049H8.75879C9.10059 14.0049 9.38086 13.7246 9.38086 13.3828C9.38086 13.041 9.10742 12.7676 8.75879 12.7676H1.56738C1.21875 12.7676 0.952148 13.041 0.952148 13.3828C0.952148 13.7246 1.22559 14.0049 1.56738 14.0049Z"></path></svg></span>함수명</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesMultipleSelect"><path d="M1.91602 4.83789C2.44238 4.83789 2.87305 4.40723 2.87305 3.87402C2.87305 3.34766 2.44238 2.91699 1.91602 2.91699C1.38281 2.91699 0.952148 3.34766 0.952148 3.87402C0.952148 4.40723 1.38281 4.83789 1.91602 4.83789ZM5.1084 4.52344H14.3984C14.7607 4.52344 15.0479 4.23633 15.0479 3.87402C15.0479 3.51172 14.7607 3.22461 14.3984 3.22461H5.1084C4.74609 3.22461 4.45898 3.51172 4.45898 3.87402C4.45898 4.23633 4.74609 4.52344 5.1084 4.52344ZM1.91602 9.03516C2.44238 9.03516 2.87305 8.60449 2.87305 8.07129C2.87305 7.54492 2.44238 7.11426 1.91602 7.11426C1.38281 7.11426 0.952148 7.54492 0.952148 8.07129C0.952148 8.60449 1.38281 9.03516 1.91602 9.03516ZM5.1084 8.7207H14.3984C14.7607 8.7207 15.0479 8.43359 15.0479 8.07129C15.0479 7.70898 14.7607 7.42188 14.3984 7.42188H5.1084C4.74609 7.42188 4.45898 7.70898 4.45898 8.07129C4.45898 8.43359 4.74609 8.7207 5.1084 8.7207ZM1.91602 13.2324C2.44238 13.2324 2.87305 12.8018 2.87305 12.2686C2.87305 11.7422 2.44238 11.3115 1.91602 11.3115C1.38281 11.3115 0.952148 11.7422 0.952148 12.2686C0.952148 12.8018 1.38281 13.2324 1.91602 13.2324ZM5.1084 12.918H14.3984C14.7607 12.918 15.0479 12.6309 15.0479 12.2686C15.0479 11.9062 14.7607 11.6191 14.3984 11.6191H5.1084C4.74609 11.6191 4.45898 11.9062 4.45898 12.2686C4.45898 12.6309 4.74609 12.918 5.1084 12.918Z"></path></svg></span>method</th><th><span class="icon property-icon"><svg viewBox="0 0 16 16" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesText"><path d="M1.56738 3.25879H14.4258C14.7676 3.25879 15.0479 2.97852 15.0479 2.63672C15.0479 2.29492 14.7744 2.02148 14.4258 2.02148H1.56738C1.21875 2.02148 0.952148 2.29492 0.952148 2.63672C0.952148 2.97852 1.22559 3.25879 1.56738 3.25879ZM1.56738 6.84082H14.4258C14.7676 6.84082 15.0479 6.56055 15.0479 6.21875C15.0479 5.87695 14.7744 5.60352 14.4258 5.60352H1.56738C1.21875 5.60352 0.952148 5.87695 0.952148 6.21875C0.952148 6.56055 1.22559 6.84082 1.56738 6.84082ZM1.56738 10.4229H14.4258C14.7676 10.4229 15.0479 10.1426 15.0479 9.80078C15.0479 9.45898 14.7744 9.18555 14.4258 9.18555H1.56738C1.21875 9.18555 0.952148 9.45898 0.952148 9.80078C0.952148 10.1426 1.22559 10.4229 1.56738 10.4229ZM1.56738 14.0049H8.75879C9.10059 14.0049 9.38086 13.7246 9.38086 13.3828C9.38086 13.041 9.10742 12.7676 8.75879 12.7676H1.56738C1.21875 12.7676 0.952148 13.041 0.952148 13.3828C0.952148 13.7246 1.22559 14.0049 1.56738 14.0049Z"></path></svg></span>비고</th></tr></thead><tbody><tr id="025f5fbd-d4c7-46ee-a006-58aaccff1126"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%89%E1%85%A2%E1%86%BC%E1%84%89%E1%85%A5%E1%86%BC%20025f5fbdd4c746eea00658aaccff1126.html">채팅방 생성</a></td><td class="cell-@TW=">createRoom</td><td class="cell-yGC="><span class="selected-value select-value-color-yellow">POST</span></td><td class="cell-fvEm"></td></tr><tr id="665370b6-7d14-4f09-ad8e-d4694c63f44b"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%20%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC%20665370b67d144f09ad8ed4694c63f44b.html">채팅방 페이지 조회</a></td><td class="cell-@TW=">pageRoom</td><td class="cell-yGC="><span class="selected-value select-value-color-blue">GET</span></td><td class="cell-fvEm"></td></tr><tr id="e8dd436c-3b02-442f-a199-43414e930c25"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%80%E1%85%A5%E1%86%B7%E1%84%89%E1%85%A2%E1%86%A8%20e8dd436c3b02442fa19943414e930c25.html">채팅방 검색</a></td><td class="cell-@TW=">findRoomName</td><td class="cell-yGC="><span class="selected-value select-value-color-blue">GET</span></td><td class="cell-fvEm"></td></tr><tr id="74f28582-adeb-4341-9e4b-743389841100"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%8B%E1%85%B2%E1%84%8C%E1%85%A5%20%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC%2074f28582adeb43419e4b743389841100.html">채팅방 유저 조회</a></td><td class="cell-@TW=">findMembersInRoom</td><td class="cell-yGC="><span class="selected-value select-value-color-blue">GET</span></td><td class="cell-fvEm"></td></tr><tr id="3ff4e3c0-2551-4402-8407-24fa5976a596"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%8C%E1%85%A1%E1%86%BC%20%E1%84%8C%E1%85%A9%E1%84%80%E1%85%A5%E1%86%AB%20%E1%84%80%E1%85%A5%E1%86%B7%E1%84%8C%E1%85%B3%E1%86%BC%203ff4e3c025514402840724fa5976a596.html">채팅방 입장 조건 검증</a></td><td class="cell-@TW=">roomEnter</td><td class="cell-yGC="><span class="selected-value select-value-color-yellow">POST</span></td><td class="cell-fvEm"></td></tr><tr id="f196ef30-af07-44af-86e0-f9d3eddfff33"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%89%E1%85%A9%E1%84%8F%E1%85%A6%E1%86%BA%20%E1%84%8C%E1%85%A5%E1%86%B8%E1%84%89%E1%85%A9%E1%86%A8%20f196ef30af0744af86e0f9d3eddfff33.html">소켓 접속</a></td><td class="cell-@TW=">configureMessageBroker</td><td class="cell-yGC="></td><td class="cell-fvEm"></td></tr><tr id="ba1729f9-0003-4840-8b8b-78181579efac"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20%E1%84%80%E1%85%AE%E1%84%83%E1%85%A9%E1%86%A8(%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%8C%E1%85%A1%E1%86%BC)%20ba1729f9000348408b8b78181579efac.html">채팅방 구독(입장)</a></td><td class="cell-@TW=">configureMessageBroker</td><td class="cell-yGC="></td><td class="cell-fvEm"></td></tr><tr id="d51a5c7c-658a-4a17-9cd9-3f5762e69ef8"><td class="cell-title"><a href="%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%8B%E1%85%A5%E1%86%B9%E1%84%8B%E1%85%B3%E1%86%B7%2095c1e33c7ff94d5bafdd19eefea96848/%E1%84%8E%E1%85%A2%E1%84%90%E1%85%B5%E1%86%BC%E1%84%87%E1%85%A1%E1%86%BC%20API%20d552484adae24447a8a80c44b76373bd/%E1%84%86%E1%85%A6%E1%84%89%E1%85%B5%E1%84%8C%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%89%E1%85%A9%E1%86%BC%20d51a5c7c658a4a179cd93f5762e69ef8.html">메시지 전송</a></td><td class="cell-@TW=">configureMessageBroker</td><td class="cell-yGC="></td><td class="cell-fvEm"></td></tr></tbody></table></div><p id="047e70db-dd47-4024-a08b-1fab81a62119" class="">
</p></div></article>

<br/>

<!--
## 구조
(프로젝트의 폴더 및 파일 구조에 대한 간략한 설명을 제공하여 코드를 참고할 때 도움이 되도록 함) 
-->
## DEMO
[링크](https://web-wchat-project-fe-7hqac2alhi4ekqp.sel4.cloudtype.app/)
