import { Container, Row, Col } from "react-bootstrap"
import '../Farmer.css';
import React, { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../../Input";
export const Farm = () => {
    const [location, setLocation] = useState("");
    const [square, setSquare] = useState("");
    const [soil, setSoil] = useState("");
    const [sunlight, setSunlight] = useState("");
    const [price, setPrice] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row>
                    <Col sm={4} xs={12} md={12} className="name">
                        Your farms
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Location</th>
                                    <th>Square</th>
                                    <th>Soil_type</th>
                                    <th>Sunlight </th>
                                    <th>Price per month</th>
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
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Banana</td>
                                    <td>400</td>
                                    <td>2023-05-05</td>
                                    <td>SPB Kronversky</td>
                                    <td>+791189798432</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={location} setValue={setLocation} id="formPlaintext" name="Location" description="Farm location" />
                        <Input value={square} setValue={setSquare} id="formPlaintext" name="Square" description="Farm square m^2" />
                        <Input value={soil} setValue={setSoil} id="formPlaintext" name="Soil_type" description="Land in Farm" />
                        <Input value={sunlight} setValue={setSunlight} id="formPlaintext" name="Sunlight" description="Sun amount" />
                        <Input value={price} setValue={setPrice} id="formPlaintext" name="Price" description="Price per month" />


                        <Button className="add_button" variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}