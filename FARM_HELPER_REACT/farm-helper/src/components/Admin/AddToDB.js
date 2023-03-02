import { Container, Row, Col } from "react-bootstrap"
import '../Farmer/Farmer.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { useParams, useLocation } from 'react-router-dom';
import { Input } from "../Input";
import { add_equipment, add_plant, get_equipments, get_plants } from "../../action/admin";
export const AddToDB = () => {
    const [plantName, setPlantName] = useState("");
    const [plantCost, setPlantCost] = useState("");
    const [plantTimeForCompleted, setPlantTimeForCompleted] = useState("");
    const [equipmentName, setEquipmentName] = useState("");
    const [equipmentCost, setEquipmentCost] = useState("");
    const [equipmentLocation, setEquipmentLocation] = useState("");
    const [plants, setPlants] = useState([]);
    const [equipments, setEquipments] = useState([]);
    const { login } = useParams();
    const { state } = useLocation();
    const auth = state.auth
    useEffect(() => {
        const fetchData = async () => {
            try {
                const plants = await get_plants(auth);
                const equipments = await get_equipments(auth);
                console.log(plants);
                console.log(equipments);
                setPlants(plants);
                setEquipments(equipments);
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
                        Add to DB
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        Plants
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>plant</th>
                                    <th>cost</th>
                                    <th>timeForCompleted</th>
                                </tr>
                            </thead>
                            <tbody>
                                {plants.map((plant) =>
                                    <tr>
                                        <td>{plant.id}</td>
                                        <td>{plant.name}</td>
                                        <td>{plant.cost}</td>
                                        <td>{plant.timeForCompleted}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={plantName} setValue={setPlantName} id="formPlaintext" name="Plant" description="Name of plant" />
                        <Input value={plantCost} setValue={setPlantCost} id="formPlaintext" name="Cost" description="Cost of plant" />
                        <Input value={plantTimeForCompleted} setValue={setPlantTimeForCompleted} id="formPlaintext" name="Times" description="timeForCompleted" />
                        <Button className="add_button" onClick={() => { add_plant(plantName, plantCost, plantTimeForCompleted, auth) }} variant="primary" type="submit">
                            Confirm
                        </Button>
                    </Col>

                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        Equipments
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>name</th>
                                    <th>cost</th>
                                    <th>location</th>
                                </tr>
                            </thead>
                            <tbody>
                                {equipments.map((equipment) =>
                                    <tr>
                                        <td>{equipment.id}</td>
                                        <td>{equipment.name}</td>
                                        <td>{equipment.cost}</td>
                                        <td>{equipment.location}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={equipmentName} setValue={setEquipmentName} id="formPlaintext" name="Name" />
                        <Input value={equipmentCost} setValue={setEquipmentCost} id="formPlaintext" name="Cost" />
                        <Input value={equipmentLocation} setValue={setEquipmentLocation} id="formPlaintext" name="Location" />
                        <Button className="add_button" onClick={() => { add_equipment(equipmentName, equipmentCost, equipmentLocation, auth) }} variant="primary" type="submit">
                            Confirm
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}