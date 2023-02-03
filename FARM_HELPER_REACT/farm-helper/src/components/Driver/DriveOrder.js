import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../Input";
export const DriveOrder = () => {
    const [plant, setPlant] = useState("");
    const [amount, setAmount] = useState("");
    const [date, setDate] = useState("");
    const [address, setAddress] = useState("");
    const [phone, setPhone] = useState("");
    const [driver, setDriver] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        ALL ORDERS
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Plant</th>
                                    <th>Amount</th>
                                    <th>Address 1</th>
                                    <th>Address 2 </th>
                                    <th>Rate Farmer</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Apple</td>
                                    <td>1000</td>
                                    <td>Moskow Krysha 12</td>
                                    <td>SPB Kronversky</td>
                                    <td>5.5</td>
                                    <td>2023-05-05</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Banana</td>
                                    <td>400</td>
                                    <td>SPB Primorky 15</td>
                                    <td>SPB Kronversky</td>
                                    <td>6.3</td>
                                    <td>2023-04-03</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={plant} setValue={setPlant} id="formPlaintext" name="Id" description="Order Id" />
                        <Button className="add_button" variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}