import { Container, Row, Col } from "react-bootstrap"
import './Profile.css';
import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import { NavLink } from "react-router-dom";
export const Profile = () => {
    return (
        <section className="main_page" id="login">
            <Container>
                <Row md={12} xs={12} xl={12} className="logo">
                    <div className="ava">
                        <div className="box">
                            <div className="circle">
                                <img src="https://thecatapi.com/api/images/get?format=src&type=png" />
                            </div>

                        </div>

                    </div>

                </Row>
                <Row md={4} xs={2} xl={12} className="buttons">
                    <Col md={4} xs={12} xl={6}>
                        <Button variant="secondary" type="submit">
                            Orders
                        </Button>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <Button variant="secondary" type="submit">
                            Your Farm
                        </Button>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <Button variant="secondary" type="submit">
                            Choose driver
                        </Button>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <Button variant="secondary" type="submit">
                            Reviews
                        </Button>
                    </Col>
                </Row>
            </Container>
        </section >
    )
}