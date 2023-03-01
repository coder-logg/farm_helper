import { Container, Row, Col } from "react-bootstrap"
import '../../Farmer/Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
export const Arbitration = () => {
    const [farmer, setFarmer] = useState("");
    const [driver, setDriver] = useState("");
    const [balance, setBalance] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        Arbitration
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Id farmer</th>
                                    <th>Id driver</th>
                                    <th>description</th>
                                    <th>Cost</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Apple</td>
                                    <td>1000</td>
                                    <td>Artur</td>
                                    <td>5.5</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Banana</td>
                                    <td>400</td>
                                    <td>Oleg</td>
                                    <td>4.3</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={farmer} setValue={setFarmer} id="formPlaintext" name="Farmer id" description="Farmer id" />
                        <Input value={driver} setValue={setDriver} id="formPlaintext" name="Driver" description="Driver id" />
                        <Input value={balance} setValue={setBalance} id="formPlaintext" name="Transaction" description="procent to farmer" />
                        <Button className="add_button" variant="primary" type="submit">
                            Confirm
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}