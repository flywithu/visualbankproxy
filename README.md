# 경기지역화폐 To 비주얼가계부

비주얼 가계부는 경기지역화폐의 사용 알림을 지원하지 않습니다.
그래서 경기지역화폐의 사용알림을 비주얼뱅크가 인식하는 형식으로 변환해서 추가 알림을 보냅니다.
KB Star Push 방식을 사용하기 때문에 KB Star push 랑 같이 사용 할 수 없습니다.

## 사용법
https://github.com/flywithu/visualbankproxy/releases

위 위치에서 다운로드 합니다.

앱을 설치 한 후 아래 아이콘으로 앱을 실행 시켜 줍니다. 

![사용법](https://github.com/flywithu/visualbankproxy/blob/main/img/20210513_132244_6.png?raw=true")

앱을 실행하면 아래와 같이 리스너 권한을 요청합니다. 권한을 승인 해주면 됩니다. (최초 1회)

<img src="https://github.com/flywithu/visualbankproxy/blob/main/img/listener.png?raw=true"/>

승인을 해주면 아래와 같은 화면이 나옵니다.  '채널'은 경기지역 화폐를 사용한 경우에 보입니다. 채널을 삭제하는 경우는 일반적으로 없습니다. 

<img src="https://github.com/flywithu/visualbankproxy/blob/main/img/20210513_132244_3.png?raw=true"/>


이제 경기 지역 화폐를 사용하면 아래와 같이 알림이 3개가 보입니다. (일반적으로 원래 2개가 나옵니다)
 - 경기 지역 화폐 알림, 이 알림을 비주얼가계부로 보내 주는 알림, 비주얼 가계부가 받은 알림.
 
### 경기지역 화폐 앱

 <img src="https://github.com/flywithu/visualbankproxy/blob/main/img/20210513_132244_4.png?raw=true"/>

### 비주얼 가계부 + AllPay2Noti 앱

 <img src="https://github.com/flywithu/visualbankproxy/blob/main/img/20210513_132244_5.png?raw=true"/>
 



## Android8 (OREO) 버젼 이상 알림 사항 사항
OREO 부터는 노티피케이션 알림에 '채널'이 사용 됩니다.
이 채널은 경기지역화폐 package name으로 설정합니다.
앱을 실행시키면 아래에서 클릭으로 채널 삭제가 가능 합니다.
(채널 삭제가 되어도 동일 앱이 카드 사용 알림을 보내면 다시 재등록 됩니다.)
### 노티피케이션채널명
 아래 예시는 경기지역화폐의 package명입니다. 
### 구별번호
 현재는 경기지역 화폐만 지원하지만, 향후를 위한 구별 번호입니다. 경기지역 화폐의 기본값은 1111 입니다.
 이 값을 수정한 후에는, 상단의 디스크 모양을 누르면 저장 됩니다. 
### 지우기 버튼
 쓰레기통 모양을 누르면 해당 채널은 삭제가 됩니다. 그러나 다시 해당 앱이 노티를 보내면 자동으로 다시 등록 됩니다.
 
<img src="https://github.com/flywithu/visualbankproxy/blob/main/img/20210514_121838.png?raw=true"/>

## 팁
 현재 KB StarPush앱으 노티 형식을 보내고 있습니다.
 그래서 KB(숫자)식으로 보이지만,
 아래처럼 별명을 입력하면 좀 더 편하게 관리 할 수 있습니다.
 
![그림5](https://github.com/flywithu/visualbankproxy/blob/main/img/20210514_121849.png?raw=true)


추가를 원하지 않으면 아래 메뉴에서 채널을 중단하면 Visual Bank에 등록이 되지 않습니다.

![그림2](https://github.com/flywithu/visualbankproxy/blob/main/img/20210513_132244_2.png?raw=true)

