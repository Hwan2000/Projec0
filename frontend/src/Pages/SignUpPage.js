import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

function SignUpPage() {
  const [CompleteSignUp, setCompleteSignUp] = useState(false);
  const navigate = useNavigate();

  const handleSignUp = () => {
    setCompleteSignUp(true);
  };

  const GoToLoginPage = () => {
    navigate("/LoginPage");
  };

  return (
    <Container>
      <Wrapper>
        {CompleteSignUp ? (
          <>
            <TitleText>Create Account</TitleText>
            <Text>계정이 성공적으로 생성되었습니다.</Text>
            <Button onClick={GoToLoginPage}>GO TO LOGIN PAGE</Button>
          </>
        ) : (
          <>
            <TitleText>Create Account</TitleText>
            <InputWrapper>
              <Input placeholder="NAME" />
              <Input placeholder="EMAIL" />
              <Input placeholder="PASSWORD" />
              <Button onClick={handleSignUp}>SIGN UP</Button>
            </InputWrapper>
          </>
        )}
      </Wrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  width: 100vw;
  height: 100vh;
  justify-content: center;
  align-items: center;
`;

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const TitleText = styled.text`
  color: white;
  font-family: Baskerville;
  font-size: 3rem;
  font-weight: 400;
  letter-spacing: -5px;
  margin-right: 5px;
  margin-bottom: 20px;
`;

const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Input = styled.input`
  width: 250px;
  height: 30px;
  margin-bottom: 15px;
  background-color: transparent;
  border-bottom: 1px solid white;
  border-top: none;
  border-right: none;
  border-left: none;
  color: white;
  font-size: 1rem;
  ::placeholder {
    color: white;
  }
`;

const Text = styled.text`
  size: 1rem;
  color: #fff;
  letter-spacing: -2px;
  margin-bottom: 15px;
  margin-top: 10px;
`;

const Button = styled.button`
  width: 255px;
  height: 35px;
  margin-top: 20px;
  background-color: white;
  color: black;
  font-size: 1rem;
  border: 1px solid white;
  cursor: pointer;
  letter-spacing: -1.5px;
`;

export default SignUpPage;
