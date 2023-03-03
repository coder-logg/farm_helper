import { Container, Row, Col } from "react-bootstrap"
import './Driver.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "../Input";
import { useParams, useLocation } from 'react-router-dom';
import { get_cars, add_car, change_car } from "../../action/driver";
export const Car = () => {
    const [mark, setMark] = useState("");
    const { login } = useParams();
    const [number, setNumber] = useState("");
    const [capacity, setCapacity] = useState("");
    const [cars, setCars] = useState({});
    const { state } = useLocation();
    const auth = state.auth;
    useEffect(() => {
        const fetchData = async () => {
            try {
                const get_car = await get_cars(login, auth);
                console.log(get_car);
                setCars(get_car);
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
                                <tr>
                                    <td>{cars.id}</td>
                                    <td>{cars.mark}</td>
                                    <td>{cars.number}</td>
                                    <td>{cars.capacity}</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                    {cars ? <Col sm={4} xs={6} md={6}>
                        <Input value={mark} setValue={setMark} id="formPlaintext" name="Mark" description="Auto Mark" />
                        <Input value={number} setValue={setNumber} id="formPlaintext" name="NumberCar" description="Number" />
                        <Input value={capacity} setValue={setCapacity} id="formPlaintext" name="Capacity" description="Capacity" />
                        <Button className="add_button" onClick={() => change_car(login, mark, number, capacity, auth)} variant="primary" type="submit">
                            Change car
                        </Button>
                    </Col> :
                        <Col sm={4} xs={6} md={6}>
                            <Input value={mark} setValue={setMark} id="formPlaintext" name="Mark" description="Auto Mark" />
                            <Input value={number} setValue={setNumber} id="formPlaintext" name="Number" description="Number" />
                            <Input value={capacity} setValue={setCapacity} id="formPlaintext" name="Capacity" description="Capacity" />
                            <Button className="add_button" onClick={() => add_car(login, mark, number, capacity, auth)} variant="primary" type="submit">
                                ADD
                            </Button>
                        </Col>
                    }
                </Row>
            </Container>
        </section >
    )
}