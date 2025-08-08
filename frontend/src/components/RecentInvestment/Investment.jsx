import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import InvestmentItem from "./InvestmentItem";
import Button from "../Button";
import { useService } from "../../api/Service";
import { useEffect, useState } from "react";
import { useGlobalContext } from "../../context/GlobalContext";

const Investment = () => {
  const { getRecentShares } = useService();
  const [shares, setShares] = useState();
  const { shouldUpdateInvestments, setShouldUpdateInvestments } = useGlobalContext();

  useEffect(() => {
    async function getStocksData() {
      try {
        const shares = await getRecentShares();
        setShares(shares);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!shares || shouldUpdateInvestments) {
      getStocksData();
      setShouldUpdateInvestments(false);
    }
  }, [getRecentShares, setShouldUpdateInvestments, shares, shouldUpdateInvestments]);

  return (
    <>
      {shares && (
        <div className="component-bg investment">
          <Container>
            <div className="investment-title">Recent Investments</div>
            <Row>
              {shares.slice(0, 5).map((share) => (
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
