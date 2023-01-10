import React from "react";
import { Container, Row, Col } from "react-bootstrap"
import Form from 'react-bootstrap/Form';
export const Input = (props) => {
    return (
        <Form.Group as={Row} className="mb-3" controlId={props.id}>
            <Form.Label column sm="2">
                {props.name}
            </Form.Label>
            <Col sm="3">
                <Form.Control onChange={(event) => props.setValue(event.target.value)}
                    value={props.value}
                    type={props.name} placeholder={props.name} />
            </Col>
        </Form.Group>
    );
}