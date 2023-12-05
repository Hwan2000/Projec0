import React from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import NavBar from "../Components/NavBar";

function HomePage() {
  const navigate = useNavigate();
  const handleTradeClick = () => {
    navigate("/TradePage", { replace: true });
  };
  // const handleWalletClick = () => {
  //   navigate("/WalletPage", { replace: true });
  // };
  // const handleAccountClick = () => {
  //   navigate("/AccountPage", { replace: true });
  // };

  return (
    <Container>
      <NavBar />
      <Menu>
        <TradeWrapper onClick={handleTradeClick}>
          <MainText>B</MainText>
          <MainText italic>uy</MainText>
          <MainText>/Se</MainText>
          <MainText barlow>ll</MainText>
          <SideText leftone>매수/매도</SideText>
        </TradeWrapper>
        <WalletWrapper>
          <SideText right>지갑</SideText>
          <MainText berkshire>W</MainText>
          <MainText italic>a</MainText>
          <MainText>ll</MainText>
          <MainText barlow>et</MainText>
        </WalletWrapper>
        <AccountWrapper>
          <MainText>Acc</MainText>
          <MainText barlow>o</MainText>
          <MainText italic>un</MainText>
          <MainText>t</MainText>
          <SideText lefttwo>계정 설정</SideText>
        </AccountWrapper>
      </Menu>
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

const Menu = styled.div`
  height: 55vh;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const TradeWrapper = styled.menu`
  margin-right: 10vw;
  height: 13vh;
  cursor: pointer;
`;

const WalletWrapper = styled.menu`
  margin-left: 10vw;
  height: 10vh;
`;

const AccountWrapper = styled.menu`
  margin-right: 10vw;
  height: 13vh;
`;

const MainText = styled.text`
  color: #fff;
  font-size: 8rem;
  letter-spacing: -0.5rem;
  font-family: Baskervville;
  ${({ italic }) =>
    italic &&
    `
      font-style: italic;
    `}
  ${({ barlow }) =>
    barlow &&
    `
      font-family: 'Barlow', sans-serif;
    `}
  ${({ berkshire }) =>
    berkshire &&
    `
      font-family: 'Berkshire Swash', serif;
    `}
`;

const SideText = styled.text`
  color: #fff;
  font-size: 1.25rem;
  letter-spacing: -0.1rem;
  ${({ leftone }) =>
    leftone &&
    `
    margin-left: 0.3vw;
  `}
  ${({ lefttwo }) =>
    lefttwo &&
    `
  margin-left: 1vw;
`}
  ${({ right }) =>
    right &&
    `
    margin-right: -3.2vw;
  `}
`;

export default HomePage;
