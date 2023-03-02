import { Container, Row, Col } from "react-bootstrap"
import './Farmer/Farmer.css';
import React, { useState, useEffect } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Input } from "./Input";
import Form from 'react-bootstrap/Form';
import { useParams } from 'react-router-dom';
import { add_review, get_reviews } from "../action/farmer";
export const Reviews = () => {
    const [loginToReview, setLoginToReview] = useState("");
    const [rate, setRate] = useState("");
    const [message, setMessage] = useState("");
    const { login } = useParams();
    const [reviews, setReviews] = useState([]);
    useEffect(() => {
        const fetchData = async (login) => {
            try {
                const reviews = await get_reviews();
                console.log(reviews);
                setReviews(reviews);
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
                        Reviews on your
                    </Col>
                </Row>
                <Row>
                    <Col sm={4} xs={6} md={6}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Login</th>
                                    <th>Rate</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody>
                                {reviews.map((review) =>
                                    <tr>
                                        <td>{review.id}</td>
                                        <td>{review.login}</td>
                                        <td>{review.rate}</td>
                                        <td>{review.message}</td>
                                    </tr>)}
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={4} xs={6} md={6}>
                        <Input value={loginToReview} setValue={setLoginToReview} id="formPlaintext" name="Login" description="Print login" />
                        <Input value={rate} setValue={setRate} id="formPlaintext" name="Rate" description="0-10" />
                        <Form.Group value={message} setValue={setMessage} className="mb-1" controlId="exampleForm.ControlTextarea2">
                            <Form.Label>Message</Form.Label>
                            <Form.Control as="textarea" rows={2} />
                        </Form.Group>

                        <Button className="add_button" onClick={() => { add_review(login, loginToReview, rate, message) }} variant="primary" type="submit">
                            ADD
                        </Button>
                    </Col>

                </Row>
            </Container>
        </section >
    )
}