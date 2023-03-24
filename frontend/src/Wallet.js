const Wallet = ({money, coinArray}) => {

    const rows = coinArray.map((row, index) =>
      <tr key={index}>
      <td>{index+1}</td>
      <td>{row.name}</td>
      </tr>
    )

    return(
        <div>
            <h1>Wallet</h1>
            <p>보유 현금:{money}</p>
            <table border="1">
        <thead>
          <tr>
            <th>Ranking</th>
            <th>Name</th>
            <th>Return</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
        </table>
        </div>
    )
}

export default Wallet;