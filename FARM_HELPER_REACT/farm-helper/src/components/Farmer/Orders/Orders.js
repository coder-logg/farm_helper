import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
import { useParams } from 'react-router-dom';
import { add_order } from "../../../action/order";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
export const Orders = () => {
    const [plant, setPlant] = useState("");
    const [amount, setAmount] = useState("");
    const [date, setDate] = useState("");
    const [address, setAddress] = useState("");
    const [customer, setCustomer] = useState("");
    const [driver, setDriver] = useState("");
    const { login } = useParams();
    const plants = ['Apple', 'EDIBLE', 'VARIEGATED', 'ORNAMENTAL', 'TRAILING', 'MEDICINAL', 'ROSE', 'HYDRANGEA']
    const customers = ['Chanelle Mckee',
        'Cynthia Underwood',
        'Greta Howard',
        'Stella Gamble',
        'Riya Sullivan',
        'Wilma Ayers',
        'Lenny Blevins',
        'Lili Meyer',
        'Azaan Holt',
        'Edwin Fisher']
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
                        Plant
                        <DropdownButton value={plant} id="dropdown-item-button" title={plant ? plant : "Choose type of plant"}>
                            {plants.map(
                                (plant_choose) => (
                                    <Dropdown.Item value={plant_choose} onClick={() => setPlant(plant_choose)} >{plant_choose}</Dropdown.Item>))}
                        </DropdownButton>
                        <Input value={amount} setValue={setAmount} id="formPlaintext" name="Amount" description="Amount plant" />
                        <Input value={date} setValue={setDate} id="formPlaintext" name="Date" description="Delivery date" />
                        <Input value={address} setValue={setAddress} id="formPlaintext" name="Address" description="Delivery address" />
                        Customer
                        <DropdownButton value={customer} id="dropdown-item-button" title={customer ? customer : "Choose name of customer"}>
                            {customers.map(
                                (customer_choose) => (
                                    <Dropdown.Item value={customer_choose} onClick={() => setCustomer(customer_choose)} >{customer_choose}</Dropdown.Item>))}
                        </DropdownButton>
                        <Button className="add_button" onClick={() => { add_order(login, date, amount, plant) }} variant="warning" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}