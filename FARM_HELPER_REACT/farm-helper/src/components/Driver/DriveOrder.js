import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import { add_order, get_orders } from "../../action/driver";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

export const DriveOrder = () => {
    const { login } = useParams();
    const [orders, setOrders] = useState([]);
    const [order, setOrder] = useState("");
    useEffect(() => {
        const fetchData = async () => {
            try {
                const orders = await get_orders(login);
                console.log(orders);
                setOrders(orders);
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
                                    <th>Farmer Name</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {orders.map((order) =>
                                    <tr>
                                        <td>{order.id}</td>
                                        <td>{order.plant}</td>
                                        <td>{order.amount}</td>
                                        <td>{order.address1}</td>
                                        <td>{order.address2}</td>
                                        <td>{order.farmerName}</td>
                                        <td>{order.date}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        Order
                        <DropdownButton value={order} id="dropdown-item-button" title={order ? order : "Choose number of order"}>
                            {orders.map(
                                (order) => (
                                    <Dropdown.Item value={order.id} onClick={() => setOrder(order.id)}>{order.id}</Dropdown.Item>))}
                        </DropdownButton>
                        <Button className="add_button" onClick={() => { add_order(login, order) }} variant="warning" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}