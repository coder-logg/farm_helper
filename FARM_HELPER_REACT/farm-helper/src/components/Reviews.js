import { Container, Row, Col } from "react-bootstrap"
import './Farmer/Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "./Input";
import Form from 'react-bootstrap/Form';
export const Reviews = () => {
    const [location, setLocation] = useState("");
    const [square, setSquare] = useState("");
    const [soil, setSoil] = useState("");
    const [sunlight, setSunlight] = useState("");
    const [price, setPrice] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        Reviews
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Login</th>
                                    <th>Rate</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Wjgewivk#21</td>
                                    <td>7</td>
                                    <td>Nice men, everything was ok</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Wfdsfewivk#21</td>
                                    <td>7</td>
                                    <td>Everything is ok</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={location} setValue={setLocation} id="formPlaintext" name="Login" description="Print login" />
                        <Input value={square} setValue={setSquare} id="formPlaintext" name="Rate" description="0-10" />
                        <Form.Group value={square} setValue={setSquare} className="mb-1" controlId="exampleForm.ControlTextarea2">
                            <Form.Label>Message</Form.Label>
                            <Form.Control as="textarea" rows={2} />
                        </Form.Group>

                        <Button className="add_button" variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}