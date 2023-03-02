import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
import { useParams, useLocation } from 'react-router-dom';
import { add_order, get_orders } from "../../../action/farmer";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { get_plants, get_customers } from "../../../action/admin";
export const Orders = () => {
    const [plant, setPlant] = useState("");
    const [amount, setAmount] = useState("");
    const [date, setDate] = useState("");
    const [address, setAddress] = useState("");
    const [customer, setCustomer] = useState("");
    const { login } = useParams();
    const [orders, setOrders] = useState([]);
    const { state } = useLocation();
    const auth = state.auth
    const [plants, setPlants] = useState([]);
    const [customers, setCustomers] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const orders = await get_orders(login, auth);
                const plants = await get_plants(auth);
                const customers = await get_customers(auth);
                console.log(orders);
                setOrders(orders);
                setPlants(plants)
                setCustomers(customers)
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
                                {orders.map((order) =>
                                    <tr>
                                        <td>{order.id}</td>
                                        <td>{order.plant}</td>
                                        <td>{order.amount}</td>
                                        <td>{order.delivery_date}</td>
                                        <td>{order.address}</td>
                                        <td>{order.customer}</td>
                                        <td>{order.driver}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        Plant
                        <DropdownButton value={plant} id="dropdown-item-button" title={plant ? plant : "Choose type of plant"}>
                            {plants.map(
                                (plant_choose) => (
                                    <Dropdown.Item value={plant_choose.id} onClick={() => setPlant(plant_choose.id)} >{plant_choose.name}</Dropdown.Item>))}
                        </DropdownButton>
                        <Input value={amount} setValue={setAmount} id="formPlaintext" name="Amount" description="Amount plant" />
                        <Input value={date} setValue={setDate} id="formPlaintext" name="Date" description="Delivery date" />
                        <Input value={address} setValue={setAddress} id="formPlaintext" name="Address" description="Delivery address" />
                        Customer
                        <DropdownButton value={customer} id="dropdown-item-button" title={customer ? customer : "Choose name of customer"}>
                            {customers.map(
                                (customer_choose) => (
                                    <Dropdown.Item value={customer_choose.id} onClick={() => setCustomer(customer_choose.id)} >{customer_choose.name}</Dropdown.Item>))}
                        </DropdownButton>
                        <Button className="add_button" onClick={() => { add_order(login, address, amount, plant, date, customer, auth) }} variant="warning" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}