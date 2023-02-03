import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../Input";
export const Car = () => {
    const [mark, setMark] = useState("");
    const [number, setNumber] = useState("");
    const [capacity, setCapacity] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        Your cars
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Mark</th>
                                    <th>Number</th>
                                    <th>Capacity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Mersedes</td>
                                    <td>10230</td>
                                    <td>2023</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Toyota</td>
                                    <td>4312</td>
                                    <td>400</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={mark} setValue={setMark} id="formPlaintext" name="Mark" description="Auto Mark" />
                        <Input value={number} setValue={setNumber} id="formPlaintext" name="Number" description="Number" />
                        <Input value={capacity} setValue={setCapacity} id="formPlaintext" name="Capacity" description="Capacity" />
                        <Button className="add_button" variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}