import Container from "react-bootstrap/esm/Container";
import "./Skeleton.css";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import { useService } from "../api/Service";
import { useEffect, useState } from "react";

const Header = () => {
  const { getUser } = useService();
  const [user, setUsers] = useState();

  useEffect(() => {
    async function getUserData() {
      try {
        const user = await getUser();
        setUsers(user);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!user) {
      getUserData();
    }
  }, [getUser, user]);

  console.log(user);
  return (
    <Container fluid style={{ width: "100%", padding: "0px" }}>
      <Row className="mx-0" id="top">
        <Col>
          {user && (
            <div>
              Available Balance: <b>${user.cash}</b>
            </div>
          )}
        </Col>
        <Col>
          <button className="funds-button">Manage Funds</button>
        </Col>
      </Row>
    </Container>
  );
};

export default Header;
