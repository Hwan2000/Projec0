import React from "react";
import styled from "styled-components";
import NavBar from "../Components/NavBar";

function TradePage() {
  return (
    <Container>
      <NavBar currentPage="TradePage" />
      sell
    </Container>
  );
}

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default TradePage;
