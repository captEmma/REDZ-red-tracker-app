import "./App.css";
import "./components/Skeleton.css";

import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Header from "./components/Header";
import Body from "./components/Body";
import Investment from "./components/RecentInvestment/Investment";
import Metric from "./components/Metric";
import ManageStocks from "./components/Stocks/ManageStocks";
import GraphContainer from "./components/Graph/GraphContainer";

function App() {
  return (
    <>
      <Header />
      <Body>
        <Col md={3}>
          <Row>
            <Metric title="Gainers" />
          </Row>
          <Row>
            <Metric title="Losers" />
          </Row>
        </Col>
        <Col md={6}>
          <GraphContainer />
        </Col>
        <Col md={3}>
          <Row id="right-container">
            <Investment />
          </Row>
          <Row>
            <ManageStocks />
          </Row>
        </Col>
      </Body>
    </>
  );
}

export default App;
