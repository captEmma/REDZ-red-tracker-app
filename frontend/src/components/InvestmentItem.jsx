import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const InvestmentItem = ({ name, sharesCount }) => {
  return (
    <Row className="investment-item mx-0">
      <Col className="investment-item-title">{name}</Col>
      <Col className="investment-item-shares-count">
        {sharesCount > 0 ? "+" : "-"} {sharesCount < 0 ? sharesCount * -1 : sharesCount} shares
      </Col>
    </Row>
  );
};

export default InvestmentItem;
