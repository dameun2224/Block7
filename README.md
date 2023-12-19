모든 Event_어쩌구 클래스가 Event 클래스를 상속받도록 했습니다.

![image](https://github.com/dameun2224/No.7/assets/113423804/34388f01-d27a-4e97-9a1d-3a67d0a8053f).
Event 클래스의 init은 Evnet 클래스를 상속받는 클래스의 객체가 생성될 때 호출됩니다. 공통적으로 처리할 수 있는 부분은 Event 클래스의 init에 작성했습니다.

![image](https://github.com/dameun2224/No.7/assets/113423804/e4bbd028-ffb8-49f4-a45e-c02a490019fe)
Event 클래스를 상속받는 클래스의 init은 해당 클래스의 객체가 생성될 때 호출됩니다. preScript, postScript의 설정과 추가로 필요한 부분은 상속받은 클래스의 init에 작성했습니다.
ex) 약탈 이벤트의 경우 따로 events_exploring 리스트로 다루기 위해 해당 클래스의 init에서만 추가되도록 했습니다.

**<Evnet 클래스>**

**val a = GameActivity.eventHandler**

GameActivity.eventHandler의 호출이 잦고, 편히 다루기 위해 Event 클래스에 a 읽기전용 변수를 추가했습니다.

**executeEventEffect()**

EventHandler 에서는 Event 클래스의 executeEventEffect() 메소드를 호출합니다. executeEventEffect() 메소드에서는 Event 클래스를 상속받은 Event_어쩌구 클래스의 eventEffect() 메소드를 호출합니다. 또한 postScript 부분을 처리합니다.

**랜덤 메소드 부분**

Event 클래스를 상속받는 Event_어쩌구 클래스의 eventEffect() 메소드에서 랜덤으로 누군가 다치거나, 아이템을 잃거나 하는 경우 Event 클래스에 작성된 랜덤 메소드를 호출하면 됩니다.

**<전체 동작 구조>**

Event_어쩌구 클래스 생성: Event의 생성자 및 init 호출 -> Event_어쩌구의 생성자 및 init 호출

이벤트 선택 및 실행: EventHandler에서 Event_어쩌구 객체 선택 ->  Event 멤버 메소드 executeEventEffect() 호출 -> Event_어쩌구 멤버 메소드 eventEffect() 호출 ..
