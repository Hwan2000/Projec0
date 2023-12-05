import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

function LoadingPage() {
  const [progress, setProgress] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const interval = setInterval(() => {
      setProgress((prevProgress) => {
        if (prevProgress >= 100) {
          clearInterval(interval);
          navigate("/MainPage", { replace: true });
          return 100;
        }
        return prevProgress + 10;
      });
    }, 1000);
  }, [navigate]);

  return (
    <Container>
      <div className="text">
        <MainText>두툼한 지갑이 무조건 좋다고 말할 수는 없다.</MainText>
        <MainText>그러나 텅 빈 지갑은 확실히 나쁘다.</MainText>
        <br />
        <MainText>-Talmud</MainText>
      </div>
      <div className="Loading">
        <LoadingBar>
          <LoadingProgress
            className="LoadingProgress"
            style={{ width: `${progress}%` }}
          />
          <LoadingText>L O A D I N G . . .</LoadingText>
        </LoadingBar>
      </div>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  height: 100vh;
  width: 100vw;
  align-items: center;
  justify-content: center;
  flex-direction: column;
`;

const MainText = styled.text`
  color: #fff;
  font-size: 1rem;
  letter-spacing: -0.1rem;
  display: flex;
  width: 25vw;
`;

const LoadingBar = styled.div`
  width: 40vw;
  height: 3vh;
  background-color: #ffffff;
  border-radius: 5px;
  overflow: hidden;
  display: flex;
  align-items: center;
  margin-top: 5vh;
  position: relative;
`;

const LoadingProgress = styled.div`
  height: 80%;
  background-color: #000;
  margin: 2px;
  border-radius: 5px;
  transition: width 3s linear;
  position: relative;
  left: 0;
`;

const LoadingText = styled.text`
  display: flex;
  justify-content: center;
  width: 40vw;
  font-family: Arvo;
  color: #ffffff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  letter-spacing: 0.5rem;
`;

export default LoadingPage;
