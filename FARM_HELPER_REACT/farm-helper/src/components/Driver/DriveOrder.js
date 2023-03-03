import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { useParams, useLocation } from 'react-router-dom';
import { completed_order, get_orders_driver } from "../../action/driver";
import { get_orders } from "../../action/farmer";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

export const DriveOrder = () => {
    const { login } = useParams();
    const [orders, setOrders] = useState([]);
    const [orderFarmer, setOrdersFarmer] = useState([]);
    const [order, setOrder] = useState("");
    const { state } = useLocation();
    const auth = state.auth
    useEffect(() => {
        const fetchData = async () => {
            try {
                const orders = await get_orders_driver(login, auth);
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
                                    <th>Order Id</th>
                                    <th>Farmer Login</th>
                                    <th>Address1</th>
                                    <th>Address2</th>
                                    <th>Closing Date</th>
                                    <th>Cost</th>
                                </tr>
                            </thead>
                            <tbody>
                                {orders.map(async (order) => {
                                    const ordersAll = await get_orders(order.farmerLogin, auth);
                                    setOrdersFarmer(ordersAll)
                                    return (
                                        <tr key={order.orderId}>
                                            <td>{order.status.location}</td>
                                            <td>{order.delieveryAddress}</td>
                                            <td>{order.farmerLogin}</td>
                                            <td>{order.closingDate}</td>
                                            <td>{order.cost}</td>
                                        </tr>
                                    );
                                })}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        Order
                        <DropdownButton value={order} id="dropdown-item-button" title={order ? order : "Choose number of order"}>
                            {orders.map(
                                (order) => (
                                    <Dropdown.Item value={order.orderId} onClick={() => setOrder(order.orderId)}>{order.orderId}</Dropdown.Item>))}
                        </DropdownButton>
                        <Button className="add_button" onClick={() => { completed_order(login, order) }} variant="warning" type="submit">
                            Completed
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}