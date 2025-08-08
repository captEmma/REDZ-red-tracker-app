import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import InvestmentItem from "./InvestmentItem";
import Button from "./Button";

const Investment = () => {
  const mockData = [
    {
      name: "company1",
      sharesCount: -10,
    },
    {
      name: "company2",
      sharesCount: 23,
    },
    {
      name: "company3",
      sharesCount: 2,
    },
    {
      name: "company1",
      sharesCount: -10,
    },
    {
      name: "company2",
      sharesCount: 23,
    },
    {
      name: "company3",
      sharesCount: 2,
    },
  ];

  return (
    <div className="component-bg investment">
      <Container>
        <div className="investment-title">Recent Investments</div>
        <Row>
          {mockData.map((data) => (
            <InvestmentItem key={data.name} name={data.name} sharesCount={data.sharesCount} />
          ))}
        </Row>
        <Row>
          <Button title="Show all" />
        </Row>
      </Container>
    </div>
  );
};

export default Investment;
