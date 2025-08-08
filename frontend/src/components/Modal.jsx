import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Modal = ({ title, children, onClose }) => {
  return (
    <>
      <div className="modal-backdrop" onClick={onClose}></div>
      <div className="modal-wrapper">
        <Container>
          <Row>
            <Col md={{ offset: 2, span: 8 }}>
              <div className="modal-title">{title}</div>
            </Col>
            <Col className="align-top-right" md={2}>
              <button className="close-button" onClick={onClose}>
                <FontAwesomeIcon icon={faXmark} size="xl" />
              </button>
            </Col>
          </Row>
          <Row>{children}</Row>
        </Container>
      </div>
    </>
  );
};

export default Modal;
