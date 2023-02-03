import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
export const ChooseDriver = () => {
    const [order, setOrder] = useState("");
    const [driver, setDriver] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        Choose driver
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Plant</th>
                                    <th>Id driver</th>
                                    <th>Name Driver</th>
                                    <th>Rate </th>
                                    <th>Car Capacity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Apple</td>
                                    <td>1000</td>
                                    <td>Artur</td>
                                    <td>5.5</td>
                                    <td>400</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Banana</td>
                                    <td>400</td>
                                    <td>Oleg</td>
                                    <td>4.3</td>
                                    <td>350</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={order} setValue={setOrder} id="formPlaintext" name="Order" description="Order id" />
                        <Input value={driver} setValue={setDriver} id="formPlaintext" name="Driver" description="Driver id" />
                        <Button className="add_button" variant="primary" type="submit">
                            Confirm
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}