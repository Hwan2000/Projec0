import React from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

function MainPage() {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/LoginPage", { replace: true });
  };

  const handleSignUpClick = () => {
    navigate("/SignUpPage", { replace: true });
  };

  return (
    <Container>
      <TextWrapper>
        <Login onClick={handleLoginClick}>LOGIN</Login>
        <SignUp onClick={handleSignUpClick}>SIGNUP</SignUp>
      </TextWrapper>
    </Container>
  );
}

const Container = styled.div`
  width: 100vw;
  height: 80vh;
  display: flex;
  align-items: center;
  justify-content: flex-start;
`;

const TextWrapper = styled.div`
  display: flex;
  margin-left: 20vw;
  flex-direction: column;
`;

const Login = styled.text`
  font-family: Baskervville;
  font-size: 7rem;
  letter-spacing: -1rem;
  transition: font-size 0.5s ease-in-out;
  cursor: pointer;
  &:hover {
    letter-spacing: 2.5rem;
  }
  color: #fff;
  line-height: 6vh;
`;

const SignUp = styled.text`
  font-family: Baskervville;
  font-size: 4.5rem;
  letter-spacing: -1rem;
  transition: font-size 0.5s ease-in-out;
  &:hover {
    letter-spacing: 1.5rem;
  }
  color: #fff;
`;

export default MainPage;
