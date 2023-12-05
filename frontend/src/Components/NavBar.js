import React from "react";
import styled from "styled-components";

function NavBar({ currentPage }) {
  return (
    <Navigator>
      <ListWrapper>
        <ListContainer>
          <List
            to={"/TradePage"}
            className={currentPage === "TradePage" ? "active" : ""}>
            Buy/Sell
          </List>
        </ListContainer>
        <ListContainer>
          <List
            to={"/WalletPage"}
            className={currentPage === "WalletPage" ? "active" : ""}>
            Wallet
          </List>
        </ListContainer>
        <ListContainer>
          <List
            to={"/AccountPage"}
            className={currentPage === "AccountPage" ? "active" : ""}>
            Account
          </List>
        </ListContainer>
      </ListWrapper>
    </Navigator>
  );
}

const Navigator = styled.nav`
  display: flex;
  color: #fff;
  position: fixed;
  top: 0;
`;

const ListWrapper = styled.ul`
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 8vh;
`;

const ListContainer = styled.li`
  list-style: none;
  display: flex;
  justify-content: center;
`;

const List = styled.text`
  text-align: center;
  font-size: 1.5rem;
  letter-spacing: -0.1rem;
  margin: 2.5vw;

  transition: width 0.3s ease;
  cursor: pointer;

  &.active {
    width: 50vw;
    &:hover {
      width: 30vw;
    }
  }

  &:hover {
    width: 50vw;
  }
`;

export default NavBar;
