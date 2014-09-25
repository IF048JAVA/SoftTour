<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Form -->
<div class="col-md-3 col-md-3-offset"></div>
    <div class="col-md-6 form">
        <form class="form-horizontal" action="#" onsubmit="return checkForm('name','email','area')">
            <fieldset>
                <legend>Форма зворотнього зв'язку</legend>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <input type="text" id="name" class="form-control input-md" name="name"
                                                 placeholder="Введіть Ваше ім'я:"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <input type="text" id="email" class="form-control input-md" name="user_email"
                                                 placeholder="Введіть Ваш e-mail:"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <select name="select" id="select" class="form-control" size="1">
                                <option value="1" class="option_style">Технічна несправність сайту</option>
                                <option value="2" class="option_style">Оформлення сайту</option>
                                <option value="3" class="option_style">Інше</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-2 col-md-2-offset"> </div>
                        <div class="col-md-8">
                            <textarea name="area" id="area" class="form-control" rows="4" placeholder="Текст повідомлення:" style="max-width: 100%"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">
                            Надіслати
                        </button>
                    </div>

            </fieldset>
        </form>
        </div>


    </div>




