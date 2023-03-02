import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
import { useParams, useLocation } from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { add_driver, get_drivers, get_orders } from "../../../action/farmer";
export const ChooseDriver = () => {
    const [order, setOrder] = useState("");
    const [driver, setDriver] = useState("");
    const { login } = useParams();
    const [drivers, setDrivers] = useState([]);
    const [orders, setOrders] = useState([]);
    const { state } = useLocation();
    const auth = state.auth
    useEffect(() => {
        const fetchData = async () => {
            try {
                const drivers = await get_drivers();
                console.log(drivers);
                setDrivers(drivers);
                const orders = await get_orders(login);
                console.log(orders);
                setOrders(orders);
            } catch (error) {
                console.log(error);
            }
        };
        fetchData();
    }, [login]);
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
                                    <th>Amount</th>
                                    <th>Delivery_Date</th>
                                    <th>Address </th>
                                    <th>Driver</th>
                                </tr>
                            </thead>
                            <tbody>
                                {orders.map((order) =>
                                    <tr>
                                        <td>{order.id}</td>
                                        <td>{order.plant}</td>
                                        <td>{order.amount}</td>
                                        <td>{order.delivery_date}</td>
                                        <td>{order.address}</td>
                                        <td>{order.driver}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>Id driver</th>
                                    <th>Name Driver</th>
                                    <th>Rate </th>
                                    <th>Car Capacity</th>
                                </tr>
                            </thead>
                            <tbody>
                                {drivers.map((driver) =>
                                    <tr>
                                        <td>{driver.id}</td>
                                        <td>{driver.name}</td>
                                        <td>{driver.rate}</td>
                                        <td>{driver.car_capacity}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={order} setValue={setOrder} id="formPlaintext" name="Order" description="Order id" />
                        Driver
                        <DropdownButton value={driver} id="dropdown-item-button" title={driver ? driver : "Choose name of driver"}>
                            {drivers.map(
                                (driver_choose) => (
                                    <Dropdown.Item value={driver_choose.name} onClick={() => setDriver(driver_choose.name)}>{driver_choose.name}</Dropdown.Item>))}
                        </DropdownButton>
                        <Button className="add_button" onClick={() => { add_driver(login, order, driver) }} variant="warning" type="submit">
                            Confirm
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}