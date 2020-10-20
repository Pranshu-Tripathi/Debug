# Debug

main Activity - > userCheckRegisteration  -> readData() -> interface FirebaseCallBack -> onCallback method 

issue -> readData return statement is exxecuted before the data is checked in onCallback
