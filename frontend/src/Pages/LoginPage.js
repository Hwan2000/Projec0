import React from "react";
import styled from "styled-components";

function LoginPage() {
  return (
    <Container>
      <Wrapper>
        <TitleText>LOGIN</TitleText>
        <InputWrapper>
          <Input placeholder="EMAIL" />
          <Input placeholder="PASSWORD" />
          <Button>SIGN IN</Button>
        </InputWrapper>
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
  flex-direction: row;
  align-items: center;
`;

const TitleText = styled.text`
  color: white;
  font-family: Baskerville;
  font-size: 3rem;
  font-weight: 400;
  letter-spacing: 23px;
`;

const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 100px;
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

export default LoginPage;
