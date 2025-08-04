import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const InvestmentItem = ({ name, amount }) => {
    return (
        <Row className="investment-item mx-0">
            <Col className="investment-item-title">{name}</Col>
            <Col className="investment-item-amount">${amount}</Col>
        </Row>
    );
};

export default InvestmentItem;
