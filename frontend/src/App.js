import React from "react";
import styled from "styled-components";
import LoadingPage from "./Pages/LoadingPage";
import MainPage from "./Pages/MainPage";
import LoginPage from "./Pages/LoginPage";
import SignUpPage from "./Pages/SignUpPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./Pages/HomePage";
import TradePage from "./Pages/TradePage";

function App() {
  return (
    <Router>
      <Container>
        <Routes>
          <Route path="/" element={<LoadingPage />} />
          <Route path="/MainPage" element={<MainPage />} />
          <Route path="/LoginPage" element={<LoginPage />} />
          <Route path="/SignUpPage" element={<SignUpPage />} />
          <Route path="/HomePage" element={<HomePage />} />
          <Route path="/TradePage" element={<TradePage />} />
        </Routes>
      </Container>
    </Router>
  );
}

const Container = styled.div`
  background-color: black;
  width: 100vw;
  height: 100vh;
`;

export default App;
