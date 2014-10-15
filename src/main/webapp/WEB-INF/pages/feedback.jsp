<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Form -->
    <div id="left_div" class="form">
        <form class="form-horizontal" action="send" id="form" method="post">
            <fieldset>
                <legend>Форма зворотнього зв'язку</legend>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <input type="text" id="name" class="form-control input-md" name="name"
                                                 placeholder="Введіть Ваше ім'я:" onblur="checkName(this)"/>
                            <span id="text_help" class="help" style="color: red"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <input type="text" id="email" class="form-control input-md" name="email"
                                                 placeholder="Введіть Ваш e-mail:" onblur="checkEmail(this)"/>
                            <span id="text_help2" class="help" style="color: red"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-3-offset"> </div>
                        <div class="col-md-6">
                            <select name="select" id="select" class="form-control" size="1">
                                <option value="Технічна несправність сайту" class="option_style">Технічна несправність сайту</option>
                                <option value="Оформлення сайту" class="option_style">Оформлення сайту</option>
                                <option value="Інше" class="option_style">Інше</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-2 col-md-2-offset"> </div>
                        <div class="col-md-8">
                            <textarea id="area" class="form-control" rows="4" name="area"  placeholder="Текст повідомлення:" onblur="checkArea(this)" style="max-width: 100%"></textarea>
                            <span id="text_help3" class="help" style="color: red"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <button class="btn btn-primary" type="submit" onclick="return checkForm(this.form)">
                            Надіслати
                        </button>
                    </div>
            </fieldset>
        </form>
    </div>

    <div id="right_div">
        <section class="foto_container">

            <input id="select-img-1" name="radio-set-1" type="radio" class="cr-selector-img-1" checked/>
            <label for="select-img-1" class="cr-label-img-1"></label>

            <input id="select-img-2" name="radio-set-1" type="radio" class="cr-selector-img-2" />
            <label for="select-img-2" class="cr-label-img-2"></label>

            <input id="select-img-3" name="radio-set-1" type="radio" class="cr-selector-img-3" />
            <label for="select-img-3" class="cr-label-img-3"></label>

            <input id="select-img-4" name="radio-set-1" type="radio" class="cr-selector-img-4" />
            <label for="select-img-4" class="cr-label-img-4"></label>

            <div class="clr"></div>
            <div class="cr-bgimg">
                <div>
                    <span>Slice 1 - Image 1</span>
                    <span>Slice 1 - Image 2</span>
                    <span>Slice 1 - Image 3</span>
                    <span>Slice 1 - Image 4</span>
                </div>
                <div>
                    <span>Slice 2 - Image 1</span>
                    <span>Slice 2 - Image 2</span>
                    <span>Slice 2 - Image 3</span>
                    <span>Slice 2 - Image 4</span>
                </div>
                <div>
                    <span>Slice 3 - Image 1</span>
                    <span>Slice 3 - Image 2</span>
                    <span>Slice 3 - Image 3</span>
                    <span>Slice 3 - Image 4</span>
                </div>
                <div>
                    <span>Slice 4 - Image 1</span>
                    <span>Slice 4 - Image 2</span>
                    <span>Slice 4 - Image 3</span>
                    <span>Slice 4 - Image 4</span>
                </div>
            </div>
            <div class="cr-titles">
                <h3><span>London</span><span>Tower Bridge</span></h3>
                <h3><span>Rome</span><span>The Colosseum (Coliseum)</span></h3>
                <h3><span>Rio de Janeiro</span><span>Christ the Redeemer</span></h3>
                <h3><span>San Francisco</span><span>The Golden Gate Bridge</span></h3>
            </div>
        </section>
    </div>







