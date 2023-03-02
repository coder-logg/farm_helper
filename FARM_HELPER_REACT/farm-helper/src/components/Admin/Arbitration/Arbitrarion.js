import { Container, Row, Col } from "react-bootstrap"
import '../../Farmer/Farmer.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
import { useParams } from 'react-router-dom';
import { add_arbitration, get_arbitration } from "../../../action/admin";
export const Arbitration = () => {
    const [orderId, setOrderId] = useState("");
    const [balance, setBalance] = useState("");
    const { login } = useParams();
    const [arbitrations, setArbitrations] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const arbitrations = await get_arbitration();
                console.log(arbitrations);
                setArbitrations(arbitrations);
            } catch (error) {
                console.log(error);
            }
        };
        fetchData();
    });
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
                                    <th>Admin Id</th>
                                    <th>Order Id</th>
                                    <th>Farmer Id </th>
                                    <th>Driver Id </th>
                                    <th>Order_For_Drive_Id</th>
                                </tr>
                            </thead>
                            <tbody>
                                {arbitrations.map((arbitration) =>
                                    <tr>
                                        <td>{arbitration.id}</td>
                                        <td>{arbitration.admin_id}</td>
                                        <td>{arbitration.order_id}</td>
                                        <td>{arbitration.driver_id}</td>
                                        <td>{arbitration.farmer_id}</td>
                                        <td>{arbitration.order_for_drive_id}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={orderId} setValue={setOrderId} id="formPlaintext" name="Order id" />
                        <Input value={balance} setValue={setBalance} id="formPlaintext" name="Transaction" description="procent to farmer" />
                        <Button className="add_button" onClick={() => { add_arbitration(login, orderId, balance) }} variant="primary" type="submit">
                            Confirm
                        </Button>
                    </Col>
                </Row>
            </Container>
        </section >
    )
}