import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import InvestmentItem from "./InvestmentItem";
import Button from "../Button";
import { useService } from "../../api/Service";
import { useEffect, useState } from "react";
import { useGlobalContext } from "../../context/GlobalContext";

const Investment = () => {
  const { getUserRecentNAmount } = useService();
  const [shares, setShares] = useState();
  const { shouldUpdateInvestments, setShouldUpdateInvestments } = useGlobalContext();

  useEffect(() => {
    async function getStocksData() {
      try {
        const shares = await getUserRecentNAmount(5);
        setShares(shares);
      } catch (error) {
        console.log(error);
      }
    }
    if (!shares || shouldUpdateInvestments) {
      getStocksData();
      setShouldUpdateInvestments(false);
    }
  }, [getUserRecentNAmount, setShouldUpdateInvestments, shares, shouldUpdateInvestments]);

  return (
    <>
      {shares && (
        <div className="component-bg investment">
          <Container>
            <div className="investment-title">Recent Investments</div>
            <Row>
              {shares.map((share) => (
                <InvestmentItem key={share.id} name={share.companyName} sharesCount={Math.round(share.shares * 100) / 100} />
              ))}
            </Row>
            <Row>
              <Button title="Show all" />
            </Row>
          </Container>
        </div>
      )}
    </>
  );
};

export default Investment;
