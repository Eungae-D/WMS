import React from "react";
import "./App.css";
import axios from "axios";

const onKakaoLogin = () => {
  window.location.href = "http://localhost:8080/oauth2/authorization/kakao";
};

function Login() {
  return (
    <div>
      <h1>Login</h1>
      <button onClick={onKakaoLogin}>Kakao Login</button>
    </div>
  );
}

const fetchData = () => {
  axios
    .get("http://localhost:8080/", { withCredentials: true })
    .then((res) => {
      alert(JSON.stringify(res.data));
    })
    .catch((error) => alert(error));
};

function App() {
  return (
    <div className="App">
      <Login />
    </div>
  );
}

export default App;
