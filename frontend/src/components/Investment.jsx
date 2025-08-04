import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import InvestmentItem from "./InvestmentItem";

const Investment = () => {
    const mockData = [
        {
            name: "company1",
            amount: 15009,
        },
        {
            name: "company2",
            amount: 8021,
        },
        {
            name: "company3",
            amount: 19215,
        },
    ];

    return (
        <div className="investment">
            <Container>
                <Row className="investment-title">
                    <Col>Investment</Col>
                    <Col className="investment-amount">[INSERT SUM]</Col>
                </Row>
                <Row>
                    {mockData.map((data) => (
                        <InvestmentItem key={data.name} name={data.name} amount={data.amount} />
                    ))}
                </Row>
            </Container>
        </div>
    );
};

export default Investment;
