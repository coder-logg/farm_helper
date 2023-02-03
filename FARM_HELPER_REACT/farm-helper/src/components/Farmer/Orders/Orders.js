import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
export const Orders = () => {
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
                                    <th>Delivery_Date</th>
                                    <th>Address </th>
                                    <th>Customer_Phone</th>
                                    <th>Driver</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Apple</td>
                                    <td>1000</td>
                                    <td>2023-05-05</td>
                                    <td>SPB Kronversky</td>
                                    <td>+791189798432</td>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Banana</td>
                                    <td>400</td>
                                    <td>2023-05-05</td>
                                    <td>SPB Kronversky</td>
                                    <td>+791189798432</td>
                                    <td>NULL</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={plant} setValue={setPlant} id="formPlaintext" name="Plant" description="Choose plant" />
                        <Input value={amount} setValue={setAmount} id="formPlaintext" name="Amount" description="Amount plant" />
                        <Input value={date} setValue={setDate} id="formPlaintext" name="Date" description="Delivery date" />
                        <Input value={address} setValue={setAddress} id="formPlaintext" name="Address" description="Delivery address" />
                        <Input value={phone} setValue={setPhone} id="formPlaintet" name="Phone" />
                        <Input value={driver} setValue={setDriver} id="formPlaintext" name="Driver" />
                        <Button className="add_button" variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}