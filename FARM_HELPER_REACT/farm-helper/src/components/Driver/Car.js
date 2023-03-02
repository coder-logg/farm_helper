import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../Input";
import { useParams } from 'react-router-dom';
import { get_cars, add_car } from "../../action/driver";
export const Car = () => {
    const [mark, setMark] = useState("");
    const { login } = useParams();
    const [number, setNumber] = useState("");
    const [capacity, setCapacity] = useState("");
    const [cars, setCars] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const cars = await get_cars(login);
                console.log(cars);
                setCars(cars);
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
                        Your cars
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Mark</th>
                                    <th>Number</th>
                                    <th>Capacity</th>
                                </tr>
                            </thead>
                            <tbody>
                                {cars.map((car) =>
                                    <tr>
                                        <td>{car.id}</td>
                                        <td>{car.mark}</td>
                                        <td>{car.number}</td>
                                        <td>{car.capacity}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={mark} setValue={setMark} id="formPlaintext" name="Mark" description="Auto Mark" />
                        <Input value={number} setValue={setNumber} id="formPlaintext" name="Number" description="Number" />
                        <Input value={capacity} setValue={setCapacity} id="formPlaintext" name="Capacity" description="Capacity" />
                        <Button className="add_button" onClick={() => add_car(login, mark, number, capacity)} variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}